use std::fs;

use sujoe_lib::book::*;

fn main() {
    let s = String::from("abc");
    let a1: u8 = 3;

    let _s_slice: &str = &s; //convert String to &str
    let _a: *const u8 = &a1;

    //tuple can not loop

    //String vs &str
    let sql = get_all_sql();
    //expect
    //write file
    let path = get_path();

    fs::write(path, sql).expect("Unable to write file");
}
