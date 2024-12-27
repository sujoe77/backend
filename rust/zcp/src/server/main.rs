use std::io::{Read, Write};
use std::net::Shutdown;
use std::net::SocketAddrV4;
use std::net::{TcpListener, TcpStream};
use std::thread;

use zcp::common::{get_ip_port, BUFFER_SIZE};

//const ADDR: Ipv4Addr = Ipv4Addr::LOCALHOST;
//const ADDR: Ipv4Addr = Ipv4Addr::new(192, 168, 0, 116);
//const PORT: u16 = 8000;

fn main() {
    let (ip, port) = get_ip_port();
    let listener = TcpListener::bind(SocketAddrV4::new(ip, port)).unwrap();
    println!("{:?}", listener);

    for stream in listener.incoming() {
        match stream {
            Ok(stream) => {
                println!("New connection: {}", stream.peer_addr().unwrap());
                thread::spawn(move || handle_client(stream));
            }
            Err(err) => println!("Connection failed due to {:?}", err),
        }
    }
}

fn handle_client(mut stream: TcpStream) {
    let mut data = [0 as u8; BUFFER_SIZE];
    while match stream.read(&mut data) {
        Ok(size) => {
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
