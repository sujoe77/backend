use regex::Regex;
use std::fs::read_to_string;

fn main() {
    println!("Hello, world!?");

    let file_name = "./text.txt";
    let lines = read_lines(&file_name);
    println!("{}", file_name);
    for line in lines {
        println!("{}", line);
    }
}

fn regex_func(){
    let re = Regex::new(r"^\d{4}-\d{2}-\d{2}$").unwrap();
    println!("Did our date match? {}", re.is_match("2014-01-01"));
}


fn read_lines(filename: &str) -> Vec<String> {
    let mut result = Vec::new();

    for line in read_to_string(filename).unwrap().lines() {
        result.push(line.to_string())
    }

    result
}
