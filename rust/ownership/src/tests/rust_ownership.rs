use crate::rust_ownership::*;

#[test]
fn test_take_share() {
    use crate::rust_ownership::take_share;

    let s = String::from("value");
    let mut s_mutable = String::from("value2");
    let sp = &String::from("value");
    let sp_mutable = &mut String::from("value");

    take_share(sp);
    take_share(&s);
    take_share(&s_mutable);
    take_share(sp_mutable);

    print!("{}", s);
    print!("{}", s_mutable);
    print!("{}", sp);
    print!("{}", sp_mutable);
}

#[test]
fn test_take() {
    use crate::rust_ownership::take;

    let s = String::from("value");
    let mut mutable_s = String::from("value2");
    take(s);
    //println!("s is {}", s); //can not compile
    take(mutable_s);
}

#[test]
fn test_take_mutable() {
    use crate::rust_ownership::take_mutable;

    let _s = String::from("value");
    let mut mutable_s = String::from("value2");

    //takeMutable(&mut s); does not compile, s is immutable
    take_mutable(&mut mutable_s);
    println!("{}", mutable_s);
    //takeMutable(&mut s); //not compile, s immutable
}

#[test]
fn test_change() {
    let mut s = String::from("str");
    change(&mut s);
    s.push_str("s");
    let s1 = &mut s;
    change(s1);
    change(s1);

    pass_ref(&s);

    let _s_immutable = String::from("hi");
}

#[test]
fn test_pass_ref() {
    let str = String::from("hello");
    let str2 = str;
    let str2_ref = &str2;
    pass_ref(str2_ref);
    pass_ref(&str2);
    print!("{}", str2);
}

#[test]
fn test_takes_and_gives_back() {
    let str = String::from("hello");
    let str2 = takes_and_gives_back(str);
    //print!("{}", str);  this failed
    print!("{}", str2);
}

#[test]
fn test_copy_init() {
    copy_int();
}

#[test]
fn test_clone_str() {
    clone_str();
}

#[test]
pub fn test_use_after_assign() {
    use_after_assign();
}
