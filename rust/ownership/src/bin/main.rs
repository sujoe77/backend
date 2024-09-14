use ownership::rust_ownership::*;

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
