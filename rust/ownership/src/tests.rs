#[cfg(test)]
mod tests {
    use crate::my_struct::Rectangle;

    #[test]
    fn it_works() {
        let result = 2 + 2;
        assert_eq!(result, 4);
    }

    #[test]
    fn test_rectangle() {
        let scale = 2.0;
        let rect1 = Rectangle {
            width: dbg!(30.0 * scale),
            height: 50.0,
        };
    
        print!("{}", rect1.area());
        print!("{}", Rectangle::area2(3.0, 15.0)); //call associated
    
        dbg!(&rect1);
    }
}

mod ownership {
    use crate::str_funcs::*;

    #[test]
    fn test_ownership(){

    }
}

mod str {
    use crate::str_funcs::*;

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