#[allow(dead_code)]

const FILE_NAME: &str = "./tests/text.txt";

#[test]
fn test_read_lines() {
    use sujoe_lib::io;
    let str_vec = io::read_lines(&FILE_NAME);
    assert_eq!(str_vec[0], "1 line");
    assert_eq!(str_vec[1], "2 line2");
    assert_eq!(str_vec[2], "3 the 3rd line!");
}
