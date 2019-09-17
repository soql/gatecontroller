import React, {Component} from 'react';
import logo from './logo.svg';
import './App.css';
import Collapsible from 'react-collapsible';

class App extends React.Component {
	 constructor(props) {
		    super(props);
		    this.state = {
		    		eventList: [],
		    		fotos: {}
		    };
		  }

	componentDidMount() {
	    fetch('http://localhost:8080/rest/list?number=5')	      
	    .then(response => response.json())
	    .then(data => this.setState({ eventList: data }));
	  }
	handleOpen(ide){	
		const copyFoto={...this.state.fotos}	
		if(this.state.fotos[ide])
			return;
		fetch('http://localhost:8080/rest/foto?id='+ide)
		.then(response => response.json())
	    .then(data => {
	    	copyFoto[ide]=data;
	    	this.setState({	    			    
	    	 	fotos: copyFoto
	    	})});
	}
	render(){		
		const { events } = this.state;
		console.log(this.state.eventList);
		return (
		<div className="App">
			  {this.state.eventList.map(event => 			  	    
			  		<Collapsible trigger={event.direction+" "+event.dateAsString} onOpen={this.handleOpen.bind(this, event.id)}>
			  		<img src={this.state.fotos && this.state.fotos[event.id] && this.state.fotos[event.id][0]} />
			  		</Collapsible>
			  	
			  )}
	    </div>)
	}
}

export default App;
