
    pub fn take(a_string: String) {    
        println!("got str: {}", a_string);
    }

    pub fn take_share(str: &String){
        println!("got str: {}", str);
    }

    pub fn take_mutable(a_string: &mut String) {    
        println!("got str: {}", a_string);
    }
