from PIL import Image, ImageDraw
import os

# Density sizes (foreground/background/monochrome are these sizes)
sizes = {
    "mdpi": 48,
    "hdpi": 72,
    "xhdpi": 96,
    "xxhdpi": 144,
    "xxxhdpi": 192,
}

# The fallback ic_launcher.png sizes are different on some densities
fallback_sizes = {
    "ldpi": 36,
    "mdpi": 48,
    "hdpi": 72,
    "xhdpi": 96,
    "xxhdpi": 144,
    "xxxhdpi": 192,
}

def draw_gear(draw, cx, cy, r, width, teeth, color, bg_color=None):
    """Draw a gear cog centered at (cx, cy) with given radius."""
    # Draw outer circle
    draw.ellipse([cx-r, cy-r, cx+r, cy+r], fill=color)
    # Draw inner hole
    inner_r = int(r * 0.55)
    if bg_color:
        draw.ellipse([cx-inner_r, cy-inner_r, cx+inner_r, cy+inner_r], fill=bg_color)
    else:
        draw.ellipse([cx-inner_r, cy-inner_r, cx+inner_r, cy+inner_r], fill=(0,0,0,0))
    # Draw teeth
    import math
    tooth_len = int(r * 0.25)
    tooth_width = max(int(r * 0.22), 2)
    for i in range(teeth):
        angle = (2 * math.pi * i / teeth) - (math.pi / 2)
        tx = cx + int((r - tooth_len/2) * math.cos(angle))
        ty = cy + int((r - tooth_len/2) * math.sin(angle))
        # Rectangular tooth (will rotate later)
        # For simplicity, draw a small circle at tooth position
        draw.ellipse([tx-tooth_width, ty-tooth_width//2, tx+tooth_width, ty+tooth_width//2], fill=color)
        # Also draw connecting rectangle
        ox = cx + int((r - tooth_len) * math.cos(angle))
        oy = cy + int((r - tooth_len) * math.sin(angle))
        draw.line([ox, oy, tx, ty], fill=color, width=max(tooth_width//2, 1))

def draw_gear_pixel(draw, cx, cy, size, color, bg_color=None):
    """Draw a simpler pixel-art style gear for small sizes."""
    # A simple 5x5 gear pattern scaled to size
    # Center circle
    inner = size // 3
    outer = size // 2 - 1
    draw.ellipse([cx-outer, cy-outer, cx+outer, cy+outer], fill=color)
    if bg_color:
        draw.ellipse([cx-inner, cy-inner, cx+inner, cy+inner], fill=bg_color)
    else:
        draw.ellipse([cx-inner, cy-inner, cx+inner, cy+inner], fill=(0,0,0,0))
    # 4 teeth (simple cross)
    t_len = size // 4
    t_w = max(size // 8, 1)
    for dx, dy in [(1,0), (-1,0), (0,1), (0,-1)]:
        tx = cx + dx * (outer + t_len//2)
        ty = cy + dy * (outer + t_len//2)
        draw.rectangle([tx-t_w, ty-t_w, tx+t_w, ty+t_w], fill=color)

def create_foreground(size):
    """Create foreground icon - grey gear on transparent."""
    img = Image.new('RGBA', (size, size), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)
    
    # Grey metallic color
    gear_color = (180, 185, 190, 255)  # light grey
    accent_color = (210, 125, 45, 255)  # gold accent for "godmode"
    
    cx = cy = size // 2
    
    if size >= 72:
        # Full gear with 8 teeth
        r = size // 2 - 2
        draw_gear_pixel(draw, cx, cy, size-4, gear_color)
        # Add a small gold/amber dot in center
        center_r = max(size // 6, 2)
        draw.ellipse([cx-center_r, cy-center_r, cx+center_r, cy+center_r], fill=accent_color)
        # Dark inner center
        inner_center = max(center_r // 2, 1)
        draw.ellipse([cx-inner_center, cy-inner_center, cx+inner_center, cy+inner_center], fill=(60, 65, 70, 255))
    else:
        # Simple gear for small sizes
        draw_gear_pixel(draw, cx, cy, size-2, gear_color)
        center_r = max(size // 6, 1)
        draw.ellipse([cx-center_r, cy-center_r, cx+center_r, cy+center_r], fill=accent_color)
    
    return img

def create_background(size):
    """Create background icon - dark grey rounded rect."""
    img = Image.new('RGBA', (size, size), (40, 42, 45, 255))
    return img

def create_monochrome(size):
    """Create monochrome icon - white gear on transparent."""
    img = Image.new('RGBA', (size, size), (0, 0, 0, 0))
    draw = ImageDraw.Draw(img)
    
    gear_color = (255, 255, 255, 255)  # white
    cx = cy = size // 2
    
    if size >= 72:
        draw_gear_pixel(draw, cx, cy, size-4, gear_color)
        center_r = max(size // 6, 2)
        draw.ellipse([cx-center_r, cy-center_r, cx+center_r, cy+center_r], fill=(0, 0, 0, 0))
    else:
        draw_gear_pixel(draw, cx, cy, size-2, gear_color)
    
    return img

def create_fallback(size):
    """Create fallback icon - background + foreground combined."""
    bg = create_background(size)
    fg = create_foreground(size)
    bg.paste(fg, (0, 0), fg)
    return bg

# Base path for android resources
base_path = "android/src/main/res"

# Generate foreground icons
for density, size in sizes.items():
    path = f"{base_path}/mipmap-{density}"
    os.makedirs(path, exist_ok=True)
    
    fg = create_foreground(size)
    fg.save(f"{path}/ic_launcher_foreground.png")
    print(f"Created {path}/ic_launcher_foreground.png ({size}x{size})")
    
    bg = create_background(size)
    bg.save(f"{path}/ic_launcher_background.png")
    print(f"Created {path}/ic_launcher_background.png ({size}x{size})")
    
    mono = create_monochrome(size)
    mono.save(f"{path}/ic_launcher_monochrome.png")
    print(f"Created {path}/ic_launcher_monochrome.png ({size}x{size})")

# Generate fallback icons (all densities including ldpi)
for density, size in fallback_sizes.items():
    path = f"{base_path}/mipmap-{density}"
    os.makedirs(path, exist_ok=True)
    
    fallback = create_fallback(size)
    fallback.save(f"{path}/ic_launcher.png")
    print(f"Created {path}/ic_launcher.png ({size}x{size})")

print("\nAll icons generated successfully!")