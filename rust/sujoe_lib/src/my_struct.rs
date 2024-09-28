pub trait Shape {
    fn area(&self) -> f32;
    fn area2(w: f32, h: f32) -> f32;
    fn perimeter(&self) -> f32;
}

#[derive(Debug)]
pub struct Rectangle {
    pub width: f32,
    pub height: f32,
}

impl Shape for Rectangle {
    fn area(&self) -> f32 {
        self.width * self.height
    }

    fn perimeter(&self) -> f32 {
        (self.width + self.height) * 2.0
    }

    fn area2(w: f32, h: f32) -> f32 {
        w * h
    }
}
