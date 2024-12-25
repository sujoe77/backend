use std::env::args;
use std::io::{Read, Write};
use std::net::Shutdown;
use std::net::TcpStream;
use std::net::{Ipv4Addr, SocketAddrV4};
use std::str::from_utf8;

const ADDR: Ipv4Addr = Ipv4Addr::new(192, 168, 50, 98);
const PORT: u16 = 8000;

fn main() -> std::io::Result<()> {
    println!("Hello Client!");

    if let Ok(mut stream) = TcpStream::connect(SocketAddrV4::new(ADDR, PORT)) {
        println!(
            "Connected to the server on {:?}",
            stream.peer_addr().unwrap()
        );

        let message = args().nth(1).expect("Please provide message!");
        match message.as_str() {
            "#END#" => stream.shutdown(Shutdown::Both).expect("Shutdown Failed!"),
            _ => {
                print!("SENT!");
                stream.write(&message.into_bytes())?;
                let mut data = [0 as u8; 60];
                stream.read(&mut data)?;
                println!("got from server: {:?}", from_utf8(&data))
            }
        }
    } else {
        println!("Couldn't connect to server...");
    }

    Ok(())
}
