import React from 'react';
import ReactDOM from 'react-dom';
import logo from './logo.svg';
import './App.css';


class App extends React.Component {
  constructor(props) {
    super(props);
    let today = new Date(),
    //get time from date
    timex = today.getHours() + ':' + today.getMinutes() + ':' + today.getSeconds();
    this.state = {
      exchangename:'',
      companycode :'',
      datee : new Date(),
      timee : timex,
      isGoing: true,
      shareprice: 0
    };
    this.submit = this.submit.bind(this);

    this.handleInputChange = this.handleInputChange.bind(this);
  }

  handleInputChange(event) {
    const target = event.target;

    const value = target.type === 'checkbox' ? target.checked : target.value;
    const name = target.name;

    this.setState({
      [name]: value
    });
  }
  submit(e) {
    e.preventDefault();
   const myInit1  = {
			method: 'POST',
			headers: { 'Content-Type': 'application/json',
			'Access-Control-Allow-Origin' : '*',
      'Vary': 'Origin'.replace,
      'Accept': 'application/json'
},
			body: JSON.stringify({
   "exchangename" :this.state.exchangename,
   "companycode" :this.state.companycode,
    "datee": this.state.datee,
    "timee" :this.state.timee,
    "shareprice":this.state.shareprice
      })
  };


let authurl  = 'http://127.0.0.1:8080/addstockprices';
//this may fail as many records in user are laredy tharer
console.log(myInit1.body);
fetch(authurl, myInit1)
.then((response) => {
  return response.json();
})
.then(function (myJson) {
  console.log(myJson);
  });
}
  
  render() {
    return (
      <form onSubmit={this.submit}>
          <label>
         ExchangeName
          <input
            name="exchangename"
           
            value={this.state.exchangename}
            onChange={this.handleInputChange} />
        </label>
        <br />
        <label>
         CompanyCode
          <input
            name="companycode"
            value={this.state.companycode}
     
            onChange={this.handleInputChange} />
        </label>
       
        <br />
        <label>
        Date
          <input
            name="date"
            type="date"
           // value={this.state.datee}
            onChange={this.handleInputChange} />
        </label>
        <br />
        <label>
        Time
          <input
            name="time"
            type="time"
           // value={this.state.timee}
            onChange={this.handleInputChange} />
        </label>
        <br />
        <label>
          Is going:
          <input
            name="isGoing"
            type="checkbox"
            checked={this.state.isGoing}
            onChange={this.handleInputChange} />
        </label>
        <br />
        <label>
      Share Price
          <input
            name="shareprice"
            type="number"
            value={this.state.shareprice}
            onChange={this.handleInputChange} />
        </label>
        
        <br />
        <button type="submit">Save now </button>
      </form>
    );
  }
}
export default App;
