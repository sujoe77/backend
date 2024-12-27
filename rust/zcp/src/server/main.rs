use std::net::SocketAddrV4;
use std::net::TcpListener;
use std::thread;

use zcp::common::{get_ip_port, read_stream, write_stream};

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
                thread::spawn(move || read_stream(&stream, write_stream, |_s| {}));
            }
            Err(err) => println!("Connection failed due to {:?}", err),
        }
    }
}
