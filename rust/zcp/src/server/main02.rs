use chrono::Local;
use std::{env, time::Duration};
//use systemstat::{saturating_sub_bytes, Platform, System};
use tokio::{
  io::AsyncWriteExt,
  net::{TcpListener, TcpStream},
};

#[tokio::main]
async fn main() {
    let mut port = String::from("4000");
    if let Some(_) = env::args().find(|f| f.starts_with("-port")) {
      port = env::args().nth(2).unwrap_or(String::from("4000"));
    } 
  
    println!("Application started");
    
    let listener = TcpListener::bind(format!("0.0.0.0:{}", port)).await.unwrap();
  
    println!("Waiting to clients... [Port: {}]", port);
  
    loop {
      match listener.accept().await {
        Ok((socket, _)) => {
          tokio::spawn(async move {
            process(socket).await;
          });
        }
        Err(e) => println!("Error in acception: {}", e),
      }
    }
  }

  async fn process(mut socket: TcpStream) {
    println!("Connected this client, Remote Addr: {:?}", socket.peer_addr().unwrap());
    socket.set_nodelay(true).unwrap();
  
    loop {
      std::thread::sleep(Duration::from_secs(1));
  
      let local = Local::now();
      let time = local.format("%H:%M:%S\n").to_string();
  
      if let Err(e) = socket.write_all(time.as_bytes()).await {
        println!("During send, socket has given error: {}", e);
        return;
      }
    }
  }