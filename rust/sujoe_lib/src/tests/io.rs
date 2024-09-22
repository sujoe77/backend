const FILE_NAME: &str = "./src/tests/text.txt";

#[test]
fn test_read_lines() {
    use crate::io;
    let str_vec = io::read_lines(&FILE_NAME);
    assert_eq!(str_vec[0], "1 line");
    assert_eq!(str_vec[1], "2 line2");
    assert_eq!(str_vec[2], "3 the 3rd line!");
}
