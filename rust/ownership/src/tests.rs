#[cfg(test)]
mod tests {
    #[test]
    fn it_works() {
        let result = 2 + 2;
        assert_eq!(result, 4);
    }
}

mod str {
    use crate::strFuncs::*;

    #[test]
    fn test_take(){
        let s = String::from("value");
        let mut mutable_s = String::from("value2");
        take(s);
        //println!("s is {}", s); //can not compile
        take(mutable_s);        
    }

    #[test]
    fn test_take_mutable(){
        let s = String::from("value");
        let mut mutable_s = String::from("value2");

        //takeMutable(&mut s); does not compile, s is immutable
        take_mutable(&mut mutable_s);
        println!("{}", mutable_s);
        //takeMutable(&mut s); //not compile, s immutable
    }

    #[test]
    fn test_take_share(){
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
}