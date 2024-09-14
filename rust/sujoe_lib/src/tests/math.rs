#[cfg(test)]

#[test]
fn it_works() {
    use crate::math::float::divide;
    let result = divide(2.0, 2.0);
    assert_eq!(result, 1.0);
}