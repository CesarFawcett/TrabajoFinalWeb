import Button from "../ui/Button";


type Template = {
  id: number;
  title: string;
  description: string;
  price: number;
  image?: string;
};

export function CardTemplate({ data }: { data: Template }) {
  return (
    <div className="border rounded-lg overflow-hidden shadow-lg hover:shadow-xl transition-shadow">
      {data.image && (
        <img 
          src={data.image} 
          alt={data.title}
          className="w-full h-48 object-cover"
        />
      )}
      <div className="p-4">
        <h3 className="text-xl font-semibold">{data.title}</h3>
        <p className="text-gray-600 my-2">{data.description}</p>
        <div className="flex justify-between items-center mt-4">
          <span className="font-bold">${data.price}</span>
          <Button>Ver Detalles</Button>
        </div>
      </div>
    </div>
  );
}