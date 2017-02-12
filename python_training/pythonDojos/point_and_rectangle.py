class Point():
    def __init__(self, x, y):
        self.x = x
        self.y = y

class Rectangle():
    def __init__(self, x, y, width, height):
        self.x = x
        self.y = y
        self.width = width
        self.height = height

    def contains_point(self, point: Point):
        return self.x < point.x < self.x + self.width and self.y < point.y < self.y + self.height

point = Point(20.5, 30.3)
rect = Rectangle(12.8, 23.2, 100, 30)
print(rect.contains_point(point))