
    pub fn take(a_string: String) {    
        println!("got str: {}", a_string);
    }

    pub fn take_share(str: &String){
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