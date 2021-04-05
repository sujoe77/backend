use std::{
    fs,
    fs::File,
    io::{self, BufRead, BufReader},
    iter::Iterator,
    path::Path,
};

fn main() {
    // --snip--
    let folder = "/media/zhou/DATA/Backup/Mint_Backup/Downloads/doc";

    //tuple can not loop
    let name_tuple = [
        format!("{}{}", folder, "/Book/index_book.txt"),
        format!("{}{}", folder, "/paper/index_paper.txt"),
    ];

    //String vs &str
    let mut sql = String::from("");
    //iterator
    for file in name_tuple.iter() {
        let mut format = "book";
        //string functions
        if file.contains("/paper/") {
            format = "paper";
        }

        let lines = lines_from_file(file).expect("Could not load lines");
        for line in lines {
            //format string
            let sql1 = format!(
                "insert into resources(format, keywords, path) values (\'{}\', \'{}\', \'{}\');\n",
                format, "", line
            );
            //concat string
            sql.push_str(&sql1);
            print!("{}", &sql1);
        }
    }
    //expect
    //write file
    fs::write(format!("{}{}", folder, "/Book/insert_resources.sql"), sql)
        .expect("Unable to write file");
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
