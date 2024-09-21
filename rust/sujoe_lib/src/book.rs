use super::io::*;

pub const FOLDER: &str = "/media/zhou/DATA/Backup/Mint_Backup/Downloads/doc";

pub fn get_all_sql() -> String {
    let index_files = [
        format!("{}{}", FOLDER, "/Book/index_book.txt"),
        format!("{}{}", FOLDER, "/paper/index_paper.txt"),
    ];
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

pub fn get_path() -> String {
    format!("{}{}", FOLDER, "/Book/insert_resources.sql")
}

pub fn get_insert(resource_type: &str, line: String) -> String {
    //format string
    let insert = format!(
        "insert into resources(format, keywords, path) values (\'{}\', \'{}\', \'{}\');\n",
        resource_type, "", line
    );
    print!("{}", &insert);
    insert
}

pub fn get_resource_type(file: &String) -> &str {
    let mut resource_type = "book";
    //string functions
    if file.contains("/paper/") {
        resource_type = "paper";
    }
    resource_type
}
