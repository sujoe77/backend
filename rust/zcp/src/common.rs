use std::io::{Read, Write};
use std::net::Shutdown;
use std::net::{Ipv4Addr, TcpStream};

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

fn handle_client(mut stream: TcpStream) {
    let mut data = [0 as u8; 60]; // using 50 byte buffer
    while match stream.read(&mut data) {
        Ok(size) => {
            // echo everything!
            stream.write(&data[0..size]).unwrap();
            true
        }
        Err(_) => {
            println!(
                "An error occurred, terminating connection with {}",
                stream.peer_addr().unwrap()
            );
            stream.shutdown(Shutdown::Both).unwrap();
            false
        }
    } {}
}
