use std::io::{Read, Write};
use std::net::Shutdown;
use std::net::{Ipv4Addr, TcpStream};

use futures::future::ok;

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

fn read_stream(mut stream: TcpStream, ok_fn: fn([u8; 60]) -> bool, err_fn: fn()) {
    let mut data = [0 as u8; 60]; // using 50 byte buffer
    while match stream.read(&mut data) {
        Ok(size) => {
            // echo everything!
            ok_fn(data)
            //stream.write(&data[0..size]).unwrap();
            //true
        }
        Err(_) => {
            err_fn();
            println!(
                "An error occurred, terminating connection with {}",
                stream.peer_addr().unwrap()
            );
            stream.shutdown(Shutdown::Both).unwrap();
            false
        }
    } {}
}
