pub fn use_after_assign() {
    let s1 = String::from("hello");
    let s2 = s1;

    println!("{}, world!", s2);

    //below does not compile
    //println!("{}, world!", s1);
}

pub fn clone_str() {
    let s1 = String::from("hello");
    let s2 = s1.clone();

    println!("s1 = {}, s2 = {}", s1, s2);
}

pub fn copy_int() {
    let x = 5;
    let y = x;

    println!("x = {}, y = {}", x, y);
}

pub fn takes_and_gives_back(a_string: String) -> String {
    //a_string.push_str(", morning!"); this does not work as a_string immutable
    a_string
}

pub fn pass_ref(a_string: &String) {
    //a_string.push_str(", morning!"); //this does not work as a_string immutable
    //*a_string
    println!("print_str got {}", a_string)
}

pub fn change(some_string: &mut String) {
    some_string.push_str(", world");
}


pub fn take(a_string: String) {
    println!("got str: {}", a_string);
}

pub fn take_share(str: &String) {
    println!("got str: {}", str);
}

pub fn take_mutable(a_string: &mut String) {
    a_string.push_str("string");
    println!("got str: {}", a_string);
}

/* this not compile, since Rust does not mut on parameter without &
pub fn take_mutable2(a_string: mut String) {
    a_string.push_str("h");
    println!("got str: {}", a_string);
}
*/