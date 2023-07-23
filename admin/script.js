const baseURL = 'http://YOURAPIADDRESS:3001';

document.getElementById('news-form').addEventListener('submit', function (event) {
  event.preventDefault();

  const title = document.getElementById('title').value;
  const date = document.getElementById('date').value;

  const newsData = {
    title: title,
    date: date
  };

  fetch(`${baseURL}/news`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(newsData)
  })
    .then(response => response.json())
    .then(data => {
      console.log(data);
      // You can update the table to display the newly added news item
      const newsList = document.getElementById('news-list');
      const newRow = document.createElement('tr');
      newRow.innerHTML = `
        <td>${data.id}</td>
        <td>${data.title}</td>
        <td>${data.date}</td>
      `;
      newsList.appendChild(newRow);
    })
    .catch(error => {
      console.error('Error:', error);
    });
});
