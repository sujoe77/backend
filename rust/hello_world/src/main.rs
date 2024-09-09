use sujoe_lib::*;

const FILE_NAME: &str = "./hello_world/text.txt";

fn main() {
    println!("Hello, world!?");

    io::read_lines(&FILE_NAME);

    str::regex_func();
    str::show_text();

    println!("{}", math::add(1, 2));
}
