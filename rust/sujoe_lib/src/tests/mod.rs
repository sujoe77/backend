#[cfg(test)]
use super::*;

#[test]
fn it_works() {
    let result = add(2, 2);
    assert_eq!(result, 4);
}

#[test]
fn test_minus() {
    let result = minus(5, 4);
    assert_eq!(result, 1)
}

#[test]
fn test_football_group_match() {
    football_group_match();
}

#[test]
fn test_int_to_array() {
    let mut array = int_to_array(0);
    assert_eq!(array, [0, 0, 0, 0, 0, 0]);

    array = int_to_array(1);
    assert_eq!(array, [0, 0, 0, 0, 0, 1]);

    array = int_to_array(2);
    assert_eq!(array, [0, 0, 0, 0, 0, 2]);

    array = int_to_array(3);
    assert_eq!(array, [0, 0, 0, 0, 1, 0]);

    array = int_to_array(4);
    assert_eq!(array, [0, 0, 0, 0, 1, 1]);

    array = int_to_array(100);
    assert_eq!(array, [0, 1, 0, 2, 0, 1]);
}