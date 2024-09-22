#[cfg(test)]
#[test]
fn it_works() {
    use crate::math::float::divide;
    let result = divide(2.0, 2.0);
    assert_eq!(result, 1.0);
}

#[test]
fn test_add() {
    use crate::math::add;
    assert_eq!(3, add(1, 2));
}
