use regex::Regex;

pub fn regex_func(){
    let re = Regex::new(r"^\d{4}-\d{2}-\d{2}$").unwrap();
    println!("Did our date match? {}", re.is_match("2014-01-01"));
}

pub fn show_text(){
    println!("show_text is called!");
}