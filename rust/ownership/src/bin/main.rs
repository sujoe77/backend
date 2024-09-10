fn main() {
    println!("Hello, world!");
    use_after_assign();
    clone_str();
    copy_int();

    let str = String::from("hello");
    let str2 = takes_and_gives_back(str);
    //print!("{}", str);  this failed
    print!("{}", str2);

    let str2_ref = &str2;
    pass_ref(str2_ref);
    pass_ref(&str2);
    print!("{}", str2);

    let mut s = String::from("str");
    change(&mut s);
    s.push_str("s");
    let s1 = &mut s;
    change(s1);
    change(s1);

    pass_ref(&s);

    let _s_immutable = String::from("hi");
}

fn use_after_assign(){
    let s1 = String::from("hello");
    let s2 = s1;

    println!("{}, world!", s2);

    //below does not compile
    //println!("{}, world!", s1);    
}

fn clone_str(){
    let s1 = String::from("hello");
    let s2 = s1.clone();

    println!("s1 = {}, s2 = {}", s1, s2);
}

fn copy_int(){
    let x = 5;
    let y = x;

    println!("x = {}, y = {}", x, y);
}

fn takes_and_gives_back(a_string: String) -> String {    
    //a_string.push_str(", morning!"); this does not work as a_string immutable
    a_string
}

fn pass_ref(a_string: &String)  {    
    //a_string.push_str(", morning!"); //this does not work as a_string immutable
    //*a_string
    println!("print_str got {}", a_string)
}

fn change(some_string: &mut String) {
    some_string.push_str(", world");
}