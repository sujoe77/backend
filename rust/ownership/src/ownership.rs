use std::{
    fs,
    fs::File,
    io::{self, BufRead, BufReader},
    iter::Iterator,
    path::Path,
};

fn main() {
    let s = String::from("shit");
    let s1 = s;
    println("string is {}", s2);
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
