function scrollToBottom() {
    window.scrollTo({
      top: document.documentElement.scrollHeight,
      behavior: 'smooth'
      
    });
  }

  window.addEventListener('scroll', function () {
    var scrollButton = document.querySelector('.scroll-button');
    if (window.scrollY > 0.2 * window.innerHeight) {
      scrollButton.style.display = 'block';
    } else {
      scrollButton.style.display = 'none';
    }
  });