use std::io::{self, Read};
use std::net::Shutdown;
use std::net::{Ipv4Addr, TcpStream};

const BUFFER_SIZE: usize = 1024;

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

pub fn read_stream(
    mut stream: &TcpStream,
    ok_fn: fn([u8; BUFFER_SIZE], usize) -> bool,
    err_fn: fn(&TcpStream) -> bool,
) {
    let mut data = [0 as u8; BUFFER_SIZE];
    while match stream.read(&mut data) {
        Ok(size) => {
            println!("read size {:?}", size);
            ok_fn(data, size);
            if size < BUFFER_SIZE {
                false
            } else {
                true
            }
        }
        Err(_) => {
            err_fn(&stream);
            println!(
                "An error occurred, terminating connection with {}",
                stream.peer_addr().unwrap()
            );
            stream.shutdown(Shutdown::Both).unwrap();
            false
        }
    } {}
}

pub fn read_stdin() -> String {
    let mut buffer = String::new();
    let _ = io::stdin().read_line(&mut buffer);
    buffer
}
