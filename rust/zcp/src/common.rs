use std::net::{Ipv4Addr, SocketAddrV4};

pub fn to_number(input: &str) -> u16 {
    input.parse().unwrap()
}

pub fn to_u8(input: &str) -> u8 {
    input.parse().unwrap()
}

pub fn to_ip(input: &str) -> Ipv4Addr {
    let parts = input.split(".");
    let collection = parts.collect::<Vec<&str>>();
    Ipv4Addr::new(
        to_u8(collection[0]),
        to_u8(collection[1]),
        to_u8(collection[2]),
        to_u8(collection[3]),
    )
}
