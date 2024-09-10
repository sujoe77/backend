use std::{
    fs::File,
    io::{self, BufRead, BufReader},
    iter::Iterator,
    path::Path,
};

fn print_str() {
    let s = String::from("shit");
    let s2 = String::from("shit");
    let s1 = s;
    assert_eq!(s1, s2);
    println!("string is {}", s1);
}

//Rust steam
//impl
//Result
fn lines_from_file(filename: impl AsRef<Path>) -> io::Result<Vec<String>> {
    BufReader::new(File::open(filename)?)
        .lines()
        .filter(|p| !(p.as_ref().unwrap()).contains("109"))
        .collect()
}

fn calculate_length(s: &String) -> usize {
    s.len()
}

mod some_func_tests {
    use crate::some_func::print_str;

    #[test]
    fn test_print_str() {
        print_str();
    }
}
