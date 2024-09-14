#[test]
fn test_area() {
    use crate::my_struct::Rectangle;
    let r = Rectangle {
        width: 4.0,
        height: 8.0,
    };
    assert_eq!(r.area(), 32.0);
}

#[test]
fn test_perimeter() {
    use crate::my_struct::Rectangle;
    let r = Rectangle {
        width: 4.0,
        height: 8.0,
    };
    assert_eq!(dbg!(r.perimeter()), 24.0);
}
