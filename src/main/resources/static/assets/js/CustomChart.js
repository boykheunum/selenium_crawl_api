$(document).ready(function () {
    $.ajax({
        type: "POST",
        url: "/hacom/chart/get_chart_data",
        success: function (data) {
            dataForChart(data);
        }
    })
})

const bar1 = document.getElementById('barChart1');
const bar2 = document.getElementById('barChart2');
const line = document.getElementById('lineChart');

function dataForChart(data) {
    console.log(data);
    let hacom = [];
    let ncpc = [];
    const priceLabels = [5000000, 10000000, 15000000, 20000000, 25000000, 30000000, 40000000, 10000000000];
    let str = "";
    for (let i = 0; i < data.length; i++) {
        data[i].executionId = parseInt(data[i].executionId);
        data[i].price = numberFormatPrice(data[i].price);
        data[i].view = parseInt(data[i].view);
        if (data[i].robotId === "HACOM03") {
            hacom.push(data[i]);
            str = "hacom";
        } else if (data[i].robotId === "NCPC03") {
            ncpc.push(data[i]);
            str = "ncpc";
        }
    }
    console.log("hacom  ");
    console.log(hacom);
    console.log("ncpc  ");
    console.log(ncpc);
    myLineChart.data.datasets[0].data = [];
    myLineChart.data.datasets[1].data = [];
    let hacomViews = [0, 0, 0, 0, 0, 0, 0, 0];
    let ncpcViews = [0, 0, 0, 0, 0, 0, 0, 0];
    for (let i = 0; i < hacom.length; i++) {
        for(let j = 0; j < priceLabels.length; j++) {
            if(hacom[i].price < priceLabels[j]) {
                hacomViews[j] += hacom[i].view;
                break;
            }
        }
    }
    for (let i = 0; i < ncpc.length; i++) {
        for(let j = 0; j < priceLabels.length; j++) {
            if(ncpc[i].price < priceLabels[j]) {
                ncpcViews[j] += ncpc[i].view;
                break;
            }
        }
    }
    for(let i = 0; i < priceLabels.length; i++) {
        myLineChart.data.datasets[0].data.push(hacomViews[i]);
        myLineChart.data.datasets[1].data.push(ncpcViews[i]);
    }
    myLineChart.update();

    hacomBar.data.labels = [];
    hacomBar.data.datasets[0].data = [];
    hacom.sort(function (a, b) {return b.price - a.price});
    let limit = hacom.length < 10 ? hacom.length : 10;
    for (let i = 0; i < limit; i++) {
        hacomBar.data.labels.push(hacom[i].productKey);
        hacomBar.data.datasets[0].data.push(hacom[i].price);
    }
    hacomBar.update();

    ncpcBar.data.labels = [];
    ncpcBar.data.datasets[0].data = [];
    ncpc.sort(function (a, b) {return b.price - a.price});
    limit = ncpc.length < 10 ? ncpc.length : 10;
    for (let i = 0; i < limit; i++) {
        ncpcBar.data.labels.push(ncpc[i].productKey);
        ncpcBar.data.datasets[0].data.push(ncpc[i].price);
    }
    ncpcBar.update();
}

var hacomBar = new Chart(bar1, {
    type: 'bar',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
            data: [12, 19, 3, 5, 2, 3],
            borderWidth: 1
        }]
    },
    options: {
        plugins: {
            legend: false,
        },
        scales: {
            y: {
                beginAtZero: true,
                suggestedMax: 1000000,
                title: {
                    display: true,
                    text: 'Giá (đ)'
                }
            }
        }
    }
});
var ncpcBar = new Chart(bar2, {
    type: 'bar',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
            data: [12, 19, 3, 5, 2, 3],
            borderWidth: 1
        }]
    },
    options: {
        plugins: {
            legend: false,
        },
        scales: {
            y: {
                beginAtZero: true,
                suggestedMax: 1000000,
                title: {
                    display: true,
                    text: 'Giá (đ)'
                }
            }
        }
    }
});

// let lineChartDemo = new Chart(line, {
//     type: "line",
//     data: {
//         datasets:[{
//             backgroundColor:'rgb(200,200,198)',
//             backgroundColor:'rgb(200,200,198)',
//             data:[1,2,3,4,5]
//         },
//         {
//             backgroundColor:'rgb(20,78,198)',
//             backgroundColor:'rgb(20,78,198)',
//             data:[1,4,3,8,10]
//         }
//     ]
//     },
// })

var myLineChart = new Chart(line, {
    type: 'line',
    data: {
        labels: ["Dưới 5 triệu", "5 - 10 triệu", "10 - 15 triệu", "15 - 20 triệu", "20 - 25 triệu", "25 - 30 triệu",
            "30 - 40 triệu", "Trên 40 triệu"],
        datasets: [{
            label: "HACOM",
            backgroundColor: "rgba(78, 115, 223, 0.05)",
            borderColor: "rgba(78, 115, 223, 1)",
            pointRadius: 3,
            pointBackgroundColor: "rgba(78, 115, 223, 1)",
            pointBorderColor: "rgba(78, 115, 223, 1)",
            pointHoverRadius: 3,
            pointHoverBackgroundColor: "rgba(78, 115, 223, 1)",
            pointHoverBorderColor: "rgba(78, 115, 223, 1)",
            pointHitRadius: 10,
            pointBorderWidth: 2,
        }, {
            label: "NCPC",
            backgroundColor: "rgba(208, 15, 23, 0.05)",
            borderColor: "rgba(208, 15, 23, 1)",
            pointRadius: 3,
            pointBackgroundColor: "rgba(208, 15, 23, 1)",
            pointBorderColor: "rgba(208, 15, 23, 1)",
            pointHoverRadius: 3,
            pointHoverBackgroundColor: "rgba(208, 15, 23, 1)",
            pointHoverBorderColor: "rgba(208, 15, 23, 1)",
            pointHitRadius: 10,
            pointBorderWidth: 2,
        }],
    },
    options: {
        maintainAspectRatio: false,
        responsive: true,
        layout: {
            padding: {
                left: 10,
                right: 25,
                top: 25,
                bottom: 0
            }
        },
        interaction: {
            intersect: false,
        },
        scales: {
            x: {
                display: true,
                title: {
                    display: true,
                    text: 'Lượt xem'
                }
            },
            y: {
                display: true,
                title: {
                    display: true,
                    text: 'Số lượng'
                }
            }
        },
        legend: {
            display: false
        },
        tooltips: {
            backgroundColor: "rgb(255,255,255)",
            bodyFontColor: "#858796",
            titleMarginBottom: 10,
            titleFontColor: '#6e707e',
            titleFontSize: 14,
            borderColor: '#dddfeb',
            borderWidth: 1,
            xPadding: 15,
            yPadding: 15,
            displayColors: false,
            intersect: false,
            mode: 'index',
            caretPadding: 10
        }
    }
});

function numberFormatPrice(price) {//Ex: 18.432.000đ
    price = price.substring(0, price.length - 1).replaceAll(".", "");
    return parseFloat(price);
}