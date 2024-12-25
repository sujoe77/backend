use futures::StreamExt;
use std::env;
use tokio::net::TcpStream;
use tokio_util::codec::{Framed, LinesCodec};

#[tokio::main]
async fn main() {
    let mut addr = String::from("localhost:6379");
    if let Some(_) = env::args().find(|f| f.starts_with("-addr")) {
        addr = env::args().nth(2).expect("error input addr");
    } 
    println!("Application started");

    let stream = TcpStream::connect(addr).await.expect("Connection error");

    println!("Connected!");

    let mut frame = Framed::new(stream, LinesCodec::new());

    while let Some(Ok(line)) = frame.next().await {
        println!("{}", line);
    }
}