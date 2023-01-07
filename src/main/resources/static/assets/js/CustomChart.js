$(document).ready(function(){
    $.ajax({
        type: "POST",
        url:"/hacom/chart/get_chart_data",
        success: function(data){
            dataForChart(data);
        }
    })
})

const bar1 = document.getElementById('barChart1');
const bar2 = document.getElementById('barChart2');
const line = document.getElementById('lineChart');
function dataForChart(data){
    console.log(data);
}
new Chart(bar1, {
    type: 'bar',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
            }
        }
    }
});
new Chart(bar2, {
    type: 'bar',
    data: {
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
        datasets: [{
            label: '# of Votes',
            data: [12, 19, 3, 5, 2, 3],
            borderWidth: 1
        }]
    },
    options: {
        scales: {
            y: {
                beginAtZero: true
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
        labels: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
        datasets: [{
            backgroundColor: "rgba(78, 115, 223, 0.05)",
            borderColor: "rgba(78, 115, 223, 1)",


            data: [10000, 5000, 15000, 10000, 20000, 15000, 25000, 20000, 30000, 25000, 40000],
        }, {
            backgroundColor: "rgba(208, 15, 23, 0.05)",
            borderColor: "rgba(208, 15, 23, 1)",

            data: [12000, 6000, 10000, 16000, 25000, 15000, 25500, 20000, 30000, 25000, 50000],
        }],
    },
    options: {
        maintainAspectRatio: false,
        responsive: true,
        plugins: {
            title: {
                display: true,
                text: 'Chart.js Line Chart - Cubic interpolation mode'
            },
        },
        interaction: {
            intersect: false,
        },
        scales: {
            x: {
                display: true,
                title: {
                    display: true
                }
            },
            y: {
                display: true,
                title: {
                    display: true,
                    text: 'Value'
                },
                suggestedMin: -10,
                suggestedMax: 200
            }
        }
    }
});