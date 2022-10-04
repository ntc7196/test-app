import React from "react";
import employeeApi from "../../api/employeeApi";
import UpdateModel from "./components/updateOrCreateModel";
import EmployeeTable from "./components/table";
import { Button } from 'reactstrap';

class EmployeeList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      listEmployee: [],
      uuid: null,
      openUpdate: false
    };
    this.updateList = this.updateList.bind(this);
  }
  componentDidMount() {
    this.updateList();
  }

  updateList() {
    employeeApi.getList()
      .then(
        data => {
          this.setState({ "listEmployee": data.data })
        }
      )
      .catch(
        err => {
            alert(err.message)
            this.setState({ "listEmployee": [] })
        }
      )
  }

  render() {
    return (
      <div className="employees">
        <Button color="primary" className="button-add"
          onClick={() => this.setState({ openAddNew: true })}> Add </Button>
        <div className="col-12">
          <EmployeeTable
            data={this.state.listEmployee}
            update={(data) => { this.setState({ uuid: data }) }}
            refeshList={() => this.updateList()}
          ></EmployeeTable>
        </div>
    
        {
          this.state.uuid &&
          <UpdateModel
            uuid={this.state.uuid}
            onClose={() => this.setState({ openAddNew: false, uuid: null })}
            refeshList={() => this.updateList()}
          />
        }
        {
          this.state.openAddNew &&
          <UpdateModel
            onClose={() => this.setState({ openAddNew: false, uuid: null })}
            refeshList={() => this.updateList()}
          />
        }
      </div>
    );
  }
}

export default EmployeeList