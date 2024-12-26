use std::io::Write;
use std::net::Shutdown;
use std::net::TcpStream;
use std::net::{Ipv4Addr, SocketAddrV4};
use std::str::from_utf8;

use zcp::common::{read_stdin, read_stream};

const ADDR: Ipv4Addr = Ipv4Addr::new(192, 168, 50, 98);
const PORT: u16 = 8000;

fn main() -> std::io::Result<()> {
    println!("Hello Client!");

    if let Ok(mut stream) = TcpStream::connect(SocketAddrV4::new(ADDR, PORT)) {
        println!(
            "Connected to the server on {:?}",
            stream.peer_addr().unwrap()
        );

        //let message = args().nth(1).expect("Please provide message!");
        loop {
            let message = read_stdin();
            match message.as_str() {
                "#END#" => {
                    break;
                }
                _ => {
                    stream.write(&message.into_bytes())?;
                }
            }
            //let mut data = [0 as u8; 1024]; // using 50 byte buffer
            read_stream(
                &stream,
                |data, size| {
                    println!("got {:?} bytes from server: {:?}", size, from_utf8(&data));
                    true
                },
                |ts| {
                    println!(
                        "An error occurred, terminating connection with {}",
                        ts.peer_addr().unwrap()
                    );
                    ts.shutdown(Shutdown::Both).unwrap();
                    true
                },
            );
        }
        stream.shutdown(Shutdown::Both).expect("Shutdown Failed!");
    } else {
        println!("Couldn't connect to server...");
    }

    Ok(())
}
