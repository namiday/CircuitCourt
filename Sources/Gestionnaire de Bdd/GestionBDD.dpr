program GestionBDD;

uses
  Vcl.Forms,
  Main in 'Main.pas' {Form1},
  Createuser in 'Createuser.pas' {CreateUserForm},
  Createproduct in 'Createproduct.pas' {Form3},
  DeleteUser in 'DeleteUser.pas' {Form4},
  DeleteProduct in 'DeleteProduct.pas' {DeleteProductForm},
  UpdateUser in 'UpdateUser.pas' {UpdateUserForm},
  UpdateProduct in 'UpdateProduct.pas' {UpdateProductForm};

{$R *.res}

begin
  Application.Initialize;
  Application.MainFormOnTaskbar := True;
  Application.CreateForm(TForm1, Form1);
//  Application.CreateForm(TCreateUserForm, CreateUserForm);
//  Application.CreateForm(TCreateProductForm, CreateProductForm);
//  Application.CreateForm(TUpdateUserForm, UpdateUserForm);
//  Application.CreateForm(TUpdateProductForm, UpdateProductForm);
//  Application.CreateForm(TDeleteProductForm, DeleteProductForm);
//  Application.CreateForm(TDeleteUserForm, DeleteUserForm);
  Application.Run;
end.
