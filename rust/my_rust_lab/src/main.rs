use std::{
    fs,
    fs::File,
    io::{self, BufRead, BufReader},
    iter::Iterator,
    path::Path,
};

use sujoe_lib::io::*;

fn main() {
    // --snip--
    let folder = "/media/zhou/DATA/Backup/Mint_Backup/Downloads/doc";

    let s = String::from("abc");
    let _s_slice: &str = &s;
    let a1: u8 = 3;
    let _a: *const u8 = &a1;

    //tuple can not loop
    let index_files = [
        format!("{}{}", folder, "/Book/index_book.txt"),
        format!("{}{}", folder, "/paper/index_paper.txt"),
    ];

    //String vs &str
    let sql = get_all_sql(index_files);
    //expect
    //write file
    fs::write(format!("{}{}", folder, "/Book/insert_resources.sql"), sql)
        .expect("Unable to write file");
}

fn get_all_sql(index_files: [String; 2]) -> String {
    let mut sql = String::from("");
    for file in index_files.iter() {
        let resource_type = get_resource_type(file);
        let lines = lines_from_file(file).expect("Could not load lines");
        for line in lines {
            let insert = get_insert(resource_type, line);
            sql.push_str(&insert);
        }
    }
    sql
}

fn get_insert(resource_type: &str, line: String) -> String {
    //format string
    let insert = format!(
        "insert into resources(format, keywords, path) values (\'{}\', \'{}\', \'{}\');\n",
        resource_type, "", line
    );
    print!("{}", &insert);
    insert
}

fn get_resource_type(file: &String) -> &str {
    let mut resource_type = "book";
    //string functions
    if file.contains("/paper/") {
        resource_type = "paper";
    }
    resource_type
}
