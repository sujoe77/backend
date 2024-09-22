use crate::math::add;
use regex::Regex;

pub fn regex_func(str: &str) {
    let re = Regex::new(r"^\d{4}-\d{2}-\d{2}$").unwrap();
    println!("Did our date {} match? {}", str, re.is_match(str));
}

pub fn show_text(str: &str) {
    println!("{}", str);
}

pub fn show_maty() {
    let a: u32 = 1;
    let b: u32 = 2;
    println!("{}", add(a, b))
}

pub fn print_str() {
    let s = String::from("shit");
    let s2 = String::from("shit");
    let s1 = s;
    assert_eq!(s1, s2);
    println!("string is {}", s1);
}

#[test]
fn test_print_str() {
    use crate::str::print_str;
    print_str();
}
