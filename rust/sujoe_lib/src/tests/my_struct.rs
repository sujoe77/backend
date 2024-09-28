#[cfg(test)]
#[test]
fn test_area() {
    use crate::my_struct::Rectangle;
    use crate::my_struct::Shape;

    let r = Rectangle {
        width: 4.0,
        height: 8.0,
    };
    assert_eq!(r.area(), 32.0);
}

#[test]
fn test_perimeter() {
    use crate::my_struct::Rectangle;
    use crate::my_struct::Shape;

    let r = Rectangle {
        width: 4.0,
        height: 8.0,
    };
    assert_eq!(dbg!(r.perimeter()), 24.0);
}

#[test]
fn it_works() {
    let result = 2 + 2;
    assert_eq!(result, 4);
}

#[test]
fn test_rectangle() {
    use crate::my_struct::Rectangle;
    use crate::my_struct::Shape;

    let scale = 2.0;
    let rect1 = Rectangle {
        width: dbg!(30.0 * scale),
        height: 50.0,
    };

    print!("{}", rect1.area());
    print!("{}", Rectangle::area2(3.0, 15.0)); //call associated

    dbg!(&rect1);
}
