use std::fs::read_to_string;

use std::{
    fs,
    fs::File,
    io::{self, BufRead, BufReader},
    iter::Iterator,
    path::Path,
};

pub fn read_lines(filename: &str) -> Vec<String> {
    println!("{}", filename);
    let mut result = Vec::new();

    for line in read_to_string(filename).unwrap().lines() {
        result.push(line.to_string());
        println!("{}", line);
    }

    result
}

//Rust stream
//impl
//Result
pub fn lines_from_file(filename: impl AsRef<Path>) -> io::Result<Vec<String>> {
    BufReader::new(File::open(filename)?)
        .lines()
        .filter(|p| !(p.as_ref().unwrap()).contains("109"))
        .collect()
}
