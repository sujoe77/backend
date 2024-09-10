use std::fs;

use sujoe_lib::book::*;

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
