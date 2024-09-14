#[derive(Debug)]
pub struct Rectangle {
    pub width: f32,
    pub height: f32,
}

impl Rectangle {
    pub fn area(&self) -> f32 {
        self.width * self.height
    }

    pub fn perimeter(&self) -> f32 {
        (self.width + self.height) * 2.0
    }

    pub fn area2(w: f32, h: f32) -> f32 {
        w * h
    }
}
