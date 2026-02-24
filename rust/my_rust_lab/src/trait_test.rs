trait State {
    fn request_review() -> Box<dyn State>;
    fn approve() -> Box<dyn State>;
}

