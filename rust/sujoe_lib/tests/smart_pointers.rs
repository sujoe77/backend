#[cfg(test)]
#[test]
pub fn test_box() {
    let x = 5;
    let y = Box::new(x);
    try_box(y);
}

fn try_box(y: Box<i32>) {
    println!("y is {}",  y);
}
