mod io;
mod math;
mod str;

#[cfg(test)]
use super::football::*;

#[test]
fn it_works() {
    use super::math::add;
    let result = add(2, 2);
    assert_eq!(result, 4);
}

#[test]
fn test_minus() {
    use super::math::minus;
    let result = minus(5, 4);
    assert_eq!(result, 1)
}

#[test]
fn test_football_group_match() {
    football_group_match();
}

#[test]
fn test_get_team_index() {
    let mut k = 2;
    assert_eq!(get_team_index(k), [0, 3]);

    k = 4;
    assert_eq!(get_team_index(k), [1, 3]);

    k = 5;
    assert_eq!(get_team_index(k), [2, 3]);
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

#[test]
fn test_add() {
    use super::math::add;
    let result = add(1, 3);
    assert_eq!(result, 4);
}
