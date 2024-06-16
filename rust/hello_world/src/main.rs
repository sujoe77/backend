use std::fs::read_to_string;
use math_func::*;
use str_func::*;

pub mod math_func;
pub mod str_func;

fn main() {
    println!("Hello, world!?");

    let file_name = "./text.txt";
    let lines = read_lines(&file_name);
    println!("{}", file_name);
    for line in lines {
        println!("{}", line);
    }

    regex_func();
    show_text();

    let r = add(1, 2);
    println!("{}", r);
}

fn read_lines(filename: &str) -> Vec<String> {
    let mut result = Vec::new();

    for line in read_to_string(filename).unwrap().lines() {
        result.push(line.to_string())
    }

    result
}
