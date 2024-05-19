#[derive(Debug)]
struct Rectangle {
    width: u32,
    height: u32,
}

impl Rectangle {
    fn area(&self) -> u32 {
        self.width * self.height
    }

    /* not compile, overloading not allowed 
    fn area(w: f32, h: f32) -> f32 {
        w * h
    }
    */

    //without self, associated method
    fn area2(w: f32, h: f32) -> f32 {
        w * h
    }
}

fn main() {
    let scale = 2;
    let rect1 = Rectangle {
        width: dbg!(30 * scale),
        height: 50,
    };

    print!("{}", rect1.area());
    print!("{}", Rectangle::area2(3.0, 15.0)); //call associated

    dbg!(&rect1);
}